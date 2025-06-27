import { Component, OnInit } from '@angular/core';
import { PackageService } from './package.service';
import { PackageDto } from './package.dto';
import { FormsModule} from '@angular/forms';
import {UserService} from '../user/user.service';


@Component({
  selector: 'app-package',
  standalone: true,
  templateUrl: './package.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./package.scss']
})
export class Package implements OnInit {
  errorMessage: string | null = null;
  findID: number | undefined = undefined;
  update: boolean = false;
  packages: PackageDto[] = [];
  pkg: PackageDto = {
    trackingNumber: "",
    userID: 0,
    weight: undefined,
    dimensions: "",
    description: "",
    deliveries: []
  };

  constructor(private service: PackageService, private userService: UserService) { }

  ngOnInit(): void {
    if(this.findID != undefined) {
      this.clickFindByIDButton(this.findID);
    } else {
      this.service.getAllPackages().subscribe({
        next: (packages) => {
          this.packages = packages.sort((a,b) => (a.packageID ?? 0) - (b.packageID ?? 0));
        },
        error: (error) => {
          console.error('Failed to load packages:', error);
        }
      });
    }
  }

  clickUpdateButton(pkg: PackageDto): void {
    this.errorMessage = null;
    window.scrollTo(0, 0);
    this.pkg = { ...pkg };
    this.update = true;
  }

  onSubmit() {
    this.errorMessage = null;
    if (this.update) {
      this.userService.getUserById(this.pkg.userID).subscribe({
        error: (error) => {
          console.error('Failed to update package:', error);
          this.errorMessage = error.error.message;
          setTimeout(() => {
            this.errorMessage = null;
          }, 5000)
          window.scrollTo(0, 0);
          this.service.getPackageById(this.pkg.packageID!).subscribe((pkg) => {
            this.pkg = pkg;
          });
          this.ngOnInit();
        }
      })

      this.service.updatePackage(this.pkg).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: (error) => {
          if(this.errorMessage == null) {
            this.errorMessage = error.error.message;
            setTimeout(() => {
              this.errorMessage = null;
            }, 5000)
            window.scrollTo(0, 0);
          }
          this.service.getPackageById(this.pkg.packageID!).subscribe((pkg) => {
            this.pkg = pkg;
          });
          this.ngOnInit();
        },
      })
    }
    else {
      this.service.createPackage(this.pkg).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create user:', error);
          this.errorMessage = error.error.message;
          setTimeout(() => {
            this.errorMessage = null;
          }, 5000)
          window.scrollTo(0, 0);
        }
      });
    }
  }

  clearForm(): void {
    this.pkg = {
      trackingNumber: "",
      userID: 0,
      weight: undefined,
      dimensions: "",
      description: "",
      deliveries: []
    };
  }

  clickCancelButton(): void {
    this.errorMessage = null;
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    if (confirm('Are you sure you want to delete this package?')) {
      this.service.deletePackageByID(id).subscribe({
        next: () => {
          this.ngOnInit();
        },
        error: (error) => {
          window.scrollTo(0, 0);
          console.error('Failed to delete package', error)
          this.errorMessage = error.error.message;
          setTimeout(() => {
            this.errorMessage = null;
          }, 5000)
        }
      });
    }
  }

  clickFindByIDButton(findID: number): void {
    this.service.getPackageById(findID).subscribe((pkg) => {
      this.packages = [pkg];
    })
  }

  clickCancelFindButton(): void {
    this.findID = undefined;
    this.ngOnInit();
  }
}
