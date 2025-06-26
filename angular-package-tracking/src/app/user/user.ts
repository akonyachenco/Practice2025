import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { UserDto } from './user.dto';
import {FormsModule, Validators} from '@angular/forms';
import {PackageService} from '../package/package.service';



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
  errorMessage: string | null = null;
  findID: number | undefined = undefined;
  showList: boolean = false;
  update: boolean = false;
  users: UserDto[] = [];
  user: UserDto = { name: "", password: "", email: "", phone: "", packages: undefined };

  constructor(private service: UserService, private packageService: PackageService) { }

  ngOnInit(): void {

    this.showList = false;
    if(this.findID != undefined)
      this.clickFindByIDButton(this.findID);
    else {
      this.service.getAllUsers().subscribe({
        next: (users) => {
          this.users = users.sort((a,b) => (a.userID ?? 0) - (b.userID ?? 0));
        },
        error: (error) => {
          console.error('Failed to load users:', error);
        }
      });
    }
  }

  clickUpdateButton(user: UserDto): void {
    this.errorMessage = null;
    window.scrollTo(0, 0);
    this.user = user;
    this.update = true;
  }

  onSubmit() {
    this.errorMessage = null;
    if (this.update) {
      this.service.updateUser(this.user).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
          error: (error) => {
            console.error('Failed to update user:', error);
            this.errorMessage = error.error.message;
            this.service.getUserById(this.user.userID!).subscribe((user) => {
              this.user = user;
            });
            this.ngOnInit();
          },
      })
    } else {
      console.log(this.user);
      this.service.createUser(this.user).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create user:', error);
          this.errorMessage = error.error.message;
        }
      });
    }
  }

  clearForm(): void {
    this.user = { name: "", password: "", email: "", phone: "", packages: undefined};
  }

  clickCancelButton(): void {
    this.errorMessage = null;
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    if (confirm('Are you sure you want to delete this user?')) {
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

  clickShowListButton(id: number): void {
    this.packageService.getAllPackagesByUserID(id).subscribe((packages) => {
      this.user.packages = packages;
      this.service.getUserById(id).subscribe((user) => {
        this.user = user;
      })
      this.errorMessage = null;
      this.showList = true;
      this.update = false;
    })
  }

  clickHideListButton(): void {
    this.ngOnInit();
    this.clearForm();
  }

  clickFindByIDButton(findID: number): void {
    this.service.getUserById(findID).subscribe((user) => {
      this.users = [user];
    })
  }

  clickCancelFindButton(): void {
    this.findID = undefined;
    this.ngOnInit();
  }

}
