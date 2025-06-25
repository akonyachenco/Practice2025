import { Component, OnInit } from '@angular/core';
import { DeliveryService } from './delivery.service';
import { DeliveryDto } from './delivery.dto';
import { FormsModule } from '@angular/forms';

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
  update: boolean = false;
  deliveries: DeliveryDto[] = [];
  delivery: DeliveryDto = {
    packageID: 0,
    courierServiceID: 0,
    cost: undefined,
    estimatedDeliveryDate: undefined
  };

  constructor(private service: DeliveryService) { }

  ngOnInit(): void {
    this.service.getAllDeliveries().subscribe({
      next: (deliveries) => {
        this.deliveries = deliveries;
      },
      error: (error) => {
        console.error('Failed to load deliveries:', error);
      }
    });
  }


  clickUpdateButton(delivery: DeliveryDto): void {
    this.delivery = delivery;
    this.update = true;
  }

  onSubmit() {
    if (this.update) {
      this.service.updateDelivery(this.delivery).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to update delivery:', error);
          this.service.getDeliveryById(this.delivery.deliveryID!).subscribe((delivery) => {
            this.delivery = delivery;
          });
          this.ngOnInit();
        }
      });
    } else {
      this.service.createDelivery(this.delivery).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create delivery:', error);
        }
      });
    }
  }

  clearForm(): void {
    this.delivery = {
      packageID: 0,
      courierServiceID: 0,
      cost: undefined,
      estimatedDeliveryDate: undefined
    };
  }

  clickCancelButton(): void {
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    this.service.deleteDeliveryByID(id).subscribe({
      next: () => {
        this.ngOnInit();
      },
      error: (error) => {
        console.error('Failed to delete delivery', error);
      }
    });
  }
}
