import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsibleReport } from '../../models/report.model';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  private api = 'http://localhost:8080/api/reports/responsibles';

  constructor(private http: HttpClient) {}

  getReport(): Observable<ResponsibleReport[]> {
    return this.http.get<ResponsibleReport[]>(this.api);
  }
}
