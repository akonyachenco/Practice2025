import { Component, OnInit } from '@angular/core';
import { PackageService } from './package.service';
import { PackageDto } from './package.dto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-package',
  standalone: true,
  templateUrl: './package.html',
  imports: [FormsModule],
  styleUrls: ['./package.scss']
})
export class Package implements OnInit {
  update: boolean = false;
  packages: PackageDto[] = [];
  pkg: PackageDto = {
    trackingNumber: '',
    userID: 0,
    weight: undefined,
    dimensions: undefined,
    description: undefined
  };

  constructor(private service: PackageService) { }

  ngOnInit(): void {
    this.service.getAllPackages().subscribe({
      next: (packages) => {
        this.packages = packages;
      },
      error: (error) => {
        console.error('Failed to load packages:', error);
      }
    });
  }


  clickUpdateButton(pkg: PackageDto): void {
    this.pkg = pkg;
    this.update = true;
  }

  onSubmit() {
    if (this.update) {
      this.service.updatePackage(this.pkg).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to update package:', error);
          this.service.getPackageById(this.pkg.packageID!).subscribe((pkg) => {
            this.pkg = pkg;
          });
          this.ngOnInit();
        }
      });
    } else {
      this.service.createPackage(this.pkg).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create package:', error);
        }
      });
    }
  }

  clearForm(): void {
    this.pkg = {
      trackingNumber: '',
      userID: 0,
      weight: undefined,
      dimensions: undefined,
      description: undefined
    };
  }

  clickCancelButton(): void {
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
          console.error('Failed to delete package', error);
        }
      });
    }
  }
}
