import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { MatChipsModule } from '@angular/material/chips';

import { PartnerDialogComponent } from '../partner-dialog/partner-dialog.component';
import { PartnerService } from '../../../core/services/partner.service';
import { Partner } from '../../../models/partner.model';

@Component({
  selector: 'app-partner-list',
  imports: [
    CommonModule,
    MatDialogModule,
    MatTableModule,
    MatButtonModule,
    MatChipsModule,
    RouterModule,
  ],
  templateUrl: './partner-list.component.html',
})
export class PartnerListComponent {
  partners: Partner[] = [];

  displayedColumns = [
    'name',
    'taxNumber',
    'status',
    'qualifications',
    'actions',
  ];

  constructor(
    private partnerService: PartnerService,
    private dialog: MatDialog,
  ) {}

  ngOnInit() {
    this.loadPartners();
  }

  loadPartners() {
    this.partnerService.getPartners().subscribe((res) => {
      console.log('eredmeny: ' + res);
      this.partners = res;
    });
  }

  openCreateDialog() {
    const dialogRef = this.dialog.open(PartnerDialogComponent);
    dialogRef.afterClosed().subscribe((res) => {
      if (res) {
        this.loadPartners();
      }
    });
  }

  openEditDialog(partner: Partner) {
    const dialogRef = this.dialog.open(PartnerDialogComponent, {
      data: partner,
    });

    dialogRef.afterClosed().subscribe((res) => {
      if (res) {
        this.loadPartners();
      }
    });
  }

  toggleStatus(partner: Partner) {
    this.partnerService.toggleStatus(partner.id!).subscribe(() => {
      this.loadPartners();
    });
  }

  delete(id: number) {
    this.partnerService.deletePartner(id).subscribe(() => {
      console.log('Partner ID:', id);

      this.partners = this.partners.filter((p) => p.id !== id);
    });
  }
}
