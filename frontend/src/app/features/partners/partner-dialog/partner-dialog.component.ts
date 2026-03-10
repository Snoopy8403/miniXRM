import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
  FormGroup,
} from '@angular/forms';

import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogModule,
} from '@angular/material/dialog';

import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

import { PartnerService } from '../../../core/services/partner.service';
import { Partner } from '../../../models/partner.model';
import { TaxNumberFormatDirective } from '../../../shared/directives/tax-number-format.directive';

@Component({
  selector: 'app-partner-dialog',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    TaxNumberFormatDirective,
  ],
  templateUrl: './partner-dialog.component.html',
  styleUrls: ['./partner-dialog.component.css'],
})
export class PartnerDialogComponent implements OnInit {
  form!: FormGroup;

  qualifications: string[] = ['Aktív', 'Külföldi', 'TOP', 'Export'];

  constructor(
    private fb: FormBuilder,
    private partnerService: PartnerService,
    private dialogRef: MatDialogRef<PartnerDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Partner | null,
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      taxNumber: [
        '',
        [Validators.required, Validators.pattern(/^\d{8}-\d-\d{2}$/)],
      ],
      address: ['', Validators.required],
      status: ['ACTIVE'],
      qualifications: [[]],
    });

    if (this.data) {
      this.form.patchValue(this.data);
    }
  }

  save() {
    if (this.form.invalid) return;

    const partner = {
      ...this.form.value,
      qualifications: this.form.value.qualifications || [],
    };
    if (this.data) {
      this.partnerService
        .updatePartner(this.data.id!, partner)
        .subscribe(() => this.dialogRef.close(true));
    } else {
      this.partnerService
        .createPartner(partner)
        .subscribe(() => this.dialogRef.close(true));
    }
  }
}
