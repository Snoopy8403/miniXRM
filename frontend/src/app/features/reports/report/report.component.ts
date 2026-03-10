import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { ReportService } from '../../../core/services/report.service';
import { ResponsibleReport } from '../../../models/report.model';

@Component({
  selector: 'app-report',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './report.component.html',
})
export class ReportComponent {
  data: ResponsibleReport[] = [];

  displayedColumns = ['responsibleName', 'totalMinutes', 'partnerCount'];

  constructor(private reportService: ReportService) {}

  ngOnInit() {
    this.reportService.getReport().subscribe((res) => {
      this.data = res;
    });
  }
}
