import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
} from '@angular/forms';

import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { Partner } from '../../../models/partner.model';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { PartnerService } from '../../../core/services/partner.service';
import { ActivityService } from '../../../core/services/activity.service';
import { Activity } from '../../../models/activity.model';

@Component({
  selector: 'app-partner-detail',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
  ],
  templateUrl: './partner-detail.component.html',
  styleUrls: ['./partner-detail.component.css'],
})
export class PartnerDetailComponent {
  partnerId!: number;
  partner: any;

  activities: Activity[] = [];

  displayedColumns = ['subject', 'type', 'durationMinutes', 'responsibleName'];

  form!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private partnerService: PartnerService,
    private activityService: ActivityService,
  ) {}

  ngOnInit() {
    this.partnerId = Number(this.route.snapshot.paramMap.get('id'));

    this.form = this.fb.group({
      subject: ['', Validators.required],
      type: ['Meeting'],
      description: [''],
      durationMinutes: [0, [Validators.required, Validators.min(1)]],
      responsibleName: ['', Validators.required],
    });

    this.loadPartner();
    this.loadActivities();
  }

  loadPartner() {
    this.partnerService
      .getPartner(this.partnerId)
      .subscribe((res) => (this.partner = res));
  }

  loadActivities() {
    this.activityService
      .getPartnerActivities(this.partnerId)
      .subscribe((res) => (this.activities = res));
  }

  createActivity() {
    if (this.form.invalid) return;

    const activity = {
      ...this.form.value,
      partnerId: this.partnerId,
    };

    this.activityService.createActivity(activity).subscribe(() => {
      this.loadActivities();
      this.form.reset();
    });
  }
}
