import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { UserDto } from './user-dto';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-user',
  standalone: true,
  templateUrl: './user.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./user.scss']
})
export class User implements OnInit {
  update: boolean = false;
  users: UserDto[] = [];
  user: UserDto = { name: "", passwordHash: "", email: "", phone: "" };

  constructor(private service: UserService) { }

  ngOnInit(): void {
    this.service.getAllUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (error) => {
        console.error('Failed to load users:', error);
      }
    });
  }

  onSubmit() {
    if (this.update) {

    } else {
      this.service.createUser(this.user).subscribe(() => {
        this.ngOnInit();
        this.user = { name: "", passwordHash: "", email: "", phone: "" };
      });
    }
  }

}
