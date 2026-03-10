import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Partner } from '../../models/partner.model';

@Injectable({
  providedIn: 'root',
})
export class PartnerService {
  private api = 'http://localhost:8080/api/partners';

  constructor(private http: HttpClient) {}

  getPartners(): Observable<Partner[]> {
    return this.http.get<Partner[]>(this.api);
  }

  getPartner(id: number): Observable<Partner> {
    return this.http.get<Partner>(`${this.api}/${id}`);
  }

  createPartner(partner: Partner): Observable<Partner> {
    return this.http.post<Partner>(this.api, partner);
  }

  updatePartner(id: number, partner: any) {
    return this.http.put(`${this.api}/${id}`, partner);
  }

  toggleStatus(id: number) {
    return this.http.patch(`${this.api}/${id}/toggle-status`, {});
  }

  deletePartner(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
