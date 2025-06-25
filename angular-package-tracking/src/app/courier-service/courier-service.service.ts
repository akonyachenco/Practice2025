import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CourierServiceDto } from './courier-service.dto';

@Injectable({
  providedIn: 'root'
})
export class CourierServiceService {
  private apiUrl = "/api/courier-services";

  constructor(private http: HttpClient) { }

  getAllCourierServices(): Observable<CourierServiceDto[]> {
    return this.http.get<CourierServiceDto[]>(this.apiUrl);
  }

  createCourierService(service: CourierServiceDto): Observable<CourierServiceDto> {
    return this.http.post<CourierServiceDto>(this.apiUrl + "/create", service);
  }

  updateCourierService(service: CourierServiceDto): Observable<CourierServiceDto> {
    return this.http.put<CourierServiceDto>(this.apiUrl + "/update", service);
  }

  deleteCourierServiceByID(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + "/delete/" + id);
  }

  getCourierServiceById(id: number): Observable<CourierServiceDto> {
    return this.http.get<CourierServiceDto>(this.apiUrl + "/" + id);
  }
}
