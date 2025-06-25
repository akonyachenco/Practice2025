import { Component, OnInit } from '@angular/core';
import { CourierServiceService } from './courier-service.service';
import { CourierServiceDto } from './courier-service.dto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-courier-service',
  standalone: true,
  templateUrl: './courier-service.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./courier-service.scss']
})
export class CourierService implements OnInit {
  update: boolean = false;
  courierServices: CourierServiceDto[] = [];
  courierService: CourierServiceDto = {
    name: "",
    phone: "",
    email: undefined,
    website: undefined
  };

  constructor(private service: CourierServiceService) { }

  ngOnInit(): void {
    this.service.getAllCourierServices().subscribe({
      next: (services) => {
        this.courierServices = services;
      },
      error: (error) => {
        console.error('Failed to load courier services:', error);
      }
    });
  }

  clickUpdateButton(service: CourierServiceDto): void {
    this.courierService = service;
    this.update = true;
  }

  onSubmit() {
    if (this.update) {
      this.service.updateCourierService(this.courierService).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to update courier service:', error);
          this.service.getCourierServiceById(this.courierService.courierServiceID!).subscribe((courierService) => {
            this.courierService = courierService;
          });
          this.ngOnInit();
        }
      });
    } else {
      this.service.createCourierService(this.courierService).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create courier service:', error);
        }
      });
    }
  }

  clearForm(): void {
    this.courierService = {
      name: "",
      phone: "",
      email: undefined,
      website: undefined
    };
  }

  clickCancelButton(): void {
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    this.service.deleteCourierServiceByID(id).subscribe({
      next: () => {
        this.ngOnInit();
      },
      error: (error) => {
        console.error('Failed to delete courier service', error);
      }
    });
  }
}
