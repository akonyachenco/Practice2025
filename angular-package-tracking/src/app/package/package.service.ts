import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PackageDto } from './package.dto';

@Injectable({
  providedIn: 'root'
})
export class PackageService {
  private apiUrl = "/api/packages";

  constructor(private http: HttpClient) { }

  getAllPackages(): Observable<PackageDto[]> {
    return this.http.get<PackageDto[]>(this.apiUrl);
  }

  createPackage(pkg: PackageDto): Observable<PackageDto> {
    return this.http.post<PackageDto>(this.apiUrl + "/create", pkg);
  }

  updatePackage(pkg: PackageDto): Observable<PackageDto> {
    return this.http.put<PackageDto>(this.apiUrl + "/update", pkg);
  }

  deletePackageByID(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + "/delete/" + id);
  }

  getPackageById(id: number): Observable<PackageDto> {
    return this.http.get<PackageDto>(this.apiUrl + "/" + id);
  }
}
