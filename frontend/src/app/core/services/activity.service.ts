import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Activity } from '../../models/activity.model';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  private api = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getPartnerActivities(partnerId: number): Observable<Activity[]> {
    return this.http.get<Activity[]>(
      `${this.api}/partners/${partnerId}/activities`,
    );
  }

  createActivity(activity: any) {
    return this.http.post(`${this.api}/activities`, activity);
  }
}
