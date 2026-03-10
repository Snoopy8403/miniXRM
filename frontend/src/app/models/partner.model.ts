export interface Partner {
  id?: number;
  name: string;
  taxNumber: string;
  address: string;
  status: 'ACTIVE' | 'INACTIVE';
  qualifications: string[];
}
