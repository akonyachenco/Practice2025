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
  errorMessage: string | null = null;
  findID: number | undefined = undefined;
  update: boolean = false;
  courierServices: CourierServiceDto[] = [];
  courierService: CourierServiceDto = {
    name: "",
    phone: ""
  };

  constructor(private service: CourierServiceService) { }

  ngOnInit(): void {
    if(this.findID != undefined) {
      this.clickFindByIDButton(this.findID);
    } else {
      this.service.getAllCourierServices().subscribe({
        next: (services) => {
          this.courierServices = services;
        },
        error: (error) => {
          console.error('Failed to load courier services:', error);
        }
      });
    }
  }

  clickUpdateButton(service: CourierServiceDto): void {
    this.errorMessage = null;
    window.scrollTo(0, 0);
    this.courierService = { ...service };
    this.update = true;
  }

  onSubmit() {
    this.errorMessage = null;
    if (this.update) {
      this.service.updateCourierService(this.courierService).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to update courier service:', error);
          this.errorMessage = error.error.message;
          this.service.getCourierServiceById(this.courierService.courierServiceID!).subscribe((service) => {
            this.courierService = service;
          });
          this.ngOnInit();
        },
      })
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
      phone: ""
    };
  }

  clickCancelButton(): void {
    this.errorMessage = null;
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    if (confirm('Are you sure you want to delete this courier service?')) {
      this.service.deleteCourierServiceByID(id).subscribe({
        next: () => {
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to delete courier service', error)
        }
      });
    }
  }

  clickFindByIDButton(findID: number): void {
    this.service.getCourierServiceById(findID).subscribe((service) => {
      this.courierServices = [service];
    })
  }

  clickCancelFindButton(): void {
    this.findID = undefined;
    this.ngOnInit();
  }
}
