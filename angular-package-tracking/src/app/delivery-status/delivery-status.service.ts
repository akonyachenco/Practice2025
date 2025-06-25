import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeliveryStatusDto } from './delivery-status.dto';

@Injectable({
  providedIn: 'root'
})
export class DeliveryStatusService {
  private apiUrl = "/api/delivery-statuses";

  constructor(private http: HttpClient) { }

  getAllDeliveryStatuses(): Observable<DeliveryStatusDto[]> {
    return this.http.get<DeliveryStatusDto[]>(this.apiUrl);
  }

  createDeliveryStatus(status: DeliveryStatusDto): Observable<DeliveryStatusDto> {
    return this.http.post<DeliveryStatusDto>(this.apiUrl + "/create", status);
  }

  updateDeliveryStatus(status: DeliveryStatusDto): Observable<DeliveryStatusDto> {
    return this.http.put<DeliveryStatusDto>(this.apiUrl + "/update", status);
  }

  deleteDeliveryStatusByID(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + "/delete/" + id);
  }

  getDeliveryStatusById(id: number): Observable<DeliveryStatusDto> {
    return this.http.get<DeliveryStatusDto>(this.apiUrl + "/" + id);
  }
}
