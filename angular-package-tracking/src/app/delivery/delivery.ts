import { Component, OnInit } from '@angular/core';
import { DeliveryService } from './delivery.service';
import { DeliveryDto } from './delivery.dto';
import { FormsModule } from '@angular/forms';
import {PackageService} from '../package/package.service';
import {CourierServiceService} from '../courier-service/courier-service.service';

@Component({
  selector: 'app-delivery',
  standalone: true,
  templateUrl: './delivery.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./delivery.scss']
})
export class Delivery implements OnInit {
  errorMessage: string | null = null;
  findID: number | undefined = undefined;
  update: boolean = false;
  deliveries: DeliveryDto[] = [];
  delivery: DeliveryDto = {
    packageID: 0,
    courierServiceID: 0
  };

  constructor(private service: DeliveryService,
              private packageService: PackageService,
              private courierServiceService: CourierServiceService) { }

  ngOnInit(): void {
    if(this.findID != undefined) {
      this.clickFindByIDButton(this.findID);
    } else {
      this.service.getAllDeliveries().subscribe({
        next: (deliveries) => {
          this.deliveries = deliveries;
        },
        error: (error) => {
          console.error('Failed to load deliveries:', error);
        }
      });
    }
  }

  clickUpdateButton(delivery: DeliveryDto): void {
    this.errorMessage = null;
    window.scrollTo(0, 0);
    this.delivery = { ...delivery };
    this.update = true;
  }

  onSubmit() {
    this.errorMessage = null;
    if (this.update) {
      this.packageService.getPackageById(this.delivery.packageID).subscribe({
        error: (error) => {
          console.error('Failed to update delivery:', error);
          this.errorMessage = error.error.message;
          this.service.getDeliveryById(this.delivery.deliveryID!).subscribe((delivery) => {
            this.delivery = delivery;
          });
          this.ngOnInit();
        }
      })

      this.courierServiceService.getCourierServiceById(this.delivery.courierServiceID).subscribe({
        error: (error) => {
          console.error('Failed to update delivery:', error);
          this.errorMessage = error.error.message;
          this.service.getDeliveryById(this.delivery.deliveryID!).subscribe((delivery) => {
            this.delivery = delivery;
          });
          this.ngOnInit();
        }
      })

      this.service.updateDelivery(this.delivery).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: () => {
          this.service.getDeliveryById(this.delivery.deliveryID!).subscribe((delivery) => {
            this.delivery = delivery;
          });
          this.ngOnInit();
        }
      })
    } else {
      this.service.createDelivery(this.delivery).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create delivery:', error);
          this.errorMessage = error.error.message;
        }
      });
    }
  }

  clearForm(): void {
    this.delivery = {
      packageID: 0,
      courierServiceID: 0
    };
  }

  clickCancelButton(): void {
    this.errorMessage = null;
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    if (confirm('Are you sure you want to delete this delivery?')) {
      this.service.deleteDeliveryByID(id).subscribe({
        next: () => {
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to delete delivery', error)
        }
      });
    }
  }

  clickFindByIDButton(findID: number): void {
    this.service.getDeliveryById(findID).subscribe((delivery) => {
      this.deliveries = [delivery];
    })
  }

  clickCancelFindButton(): void {
    this.findID = undefined;
    this.ngOnInit();
  }
}
