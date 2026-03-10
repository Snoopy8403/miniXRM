export interface Activity {
  id?: number;
  subject: string;
  type: string;
  description: string;
  durationMinutes: number;
  responsibleName: string;
  partnerId: number;
}
