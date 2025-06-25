import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDto } from './user-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = "/api/users";

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(this.apiUrl);
  }

  createUser(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(this.apiUrl + "/create", user);
  }
}
