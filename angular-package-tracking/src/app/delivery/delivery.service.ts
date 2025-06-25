import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeliveryDto } from './delivery.dto';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  private apiUrl = "/api/deliveries";

  constructor(private http: HttpClient) { }

  getAllDeliveries(): Observable<DeliveryDto[]> {
    return this.http.get<DeliveryDto[]>(this.apiUrl);
  }

  createDelivery(delivery: DeliveryDto): Observable<DeliveryDto> {
    return this.http.post<DeliveryDto>(this.apiUrl + "/create", delivery);
  }

  updateDelivery(delivery: DeliveryDto): Observable<DeliveryDto> {
    return this.http.put<DeliveryDto>(this.apiUrl + "/update", delivery);
  }

  deleteDeliveryByID(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + "/delete/" + id);
  }

  getDeliveryById(id: number): Observable<DeliveryDto> {
    return this.http.get<DeliveryDto>(this.apiUrl + "/" + id);
  }
}
