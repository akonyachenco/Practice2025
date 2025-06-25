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

  clickUpdateButton(user: UserDto): void {
    this.service.getUserById(user.userID!).subscribe({
      next: () => {
        this.user = user;
      },
      error: (error) => {
        console.error('Failed to find user', error);
      }
    })
    this.update = true;
  }

  onSubmit() {
    if (this.update) {
      this.service.updateUser(this.user).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
          error: (error) => {
            console.error('Failed to update user:', error);
          }
      })
    } else {
      this.service.createUser(this.user).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create user:', error);
        }
      });
    }
  }

  clearForm(): void {
    this.user = { name: "", passwordHash: "", email: "", phone: "" };
  }

  clickCancelButton(): void {
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    this.service.deleteUserByID(id).subscribe({
      next: () => {
        this.ngOnInit();
      },
      error: (error)=>  {
        console.error('Failed to delete user', error())
    }
    });
  }

}
