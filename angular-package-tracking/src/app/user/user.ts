import {Component, OnInit} from '@angular/core';
import {UserService} from './user.service';
import {UserDto} from './user-dto';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';


@Component({
  selector: 'app-user',
  imports: [
    FormsModule,
  ],
  templateUrl: './user.html',
  styleUrl: './user.scss'
})
export class User implements OnInit{
  update: boolean = false;
  users: UserDto[] = [];
  user: UserDto = {name:"", passwordHash:"", email:""};

  constructor(private service: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.service.getAllUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (error) => {
        console.error('Failed to load users:', error)
      }
    })
  }

  onSubmit() {
    if(this.update) {

    }
    else {
      this.service.createUser(this.user).subscribe(() => {
        this.router.navigate(["/users"])
      })
    }
  }


}
