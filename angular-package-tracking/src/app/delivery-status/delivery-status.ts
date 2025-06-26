import { Component, OnInit } from '@angular/core';
import { DeliveryStatusService } from './delivery-status.service';
import { DeliveryStatusDto } from './delivery-status.dto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-delivery-status',
  standalone: true,
  templateUrl: './delivery-status.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./delivery-status.scss']
})
export class DeliveryStatus implements OnInit {
  update: boolean = false;
  statuses: DeliveryStatusDto[] = [];
  status: DeliveryStatusDto = {
    status: '',
    deliveryID: 0,
    location: undefined,
    statusDate: undefined
  };

  constructor(private service: DeliveryStatusService) { }

  ngOnInit(): void {
    this.service.getAllDeliveryStatuses().subscribe({
      next: (statuses) => {
        this.statuses = statuses;
      },
      error: (error) => {
        console.error('Failed to load delivery statuses:', error);
      }
    });
  }


  clickUpdateButton(status: DeliveryStatusDto): void {
    this.status = status;
    this.update = true;
  }

  onSubmit() {
    if (this.update) {
      this.service.updateDeliveryStatus(this.status).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to update delivery status:', error);
          this.service.getDeliveryStatusById(this.status.deliveryStatusID!).subscribe((status) => {
            this.status = status;
          });
          this.ngOnInit();
        }
      });
    } else {
      this.service.createDeliveryStatus(this.status).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create delivery status:', error);
        }
      });
    }
  }

  clearForm(): void {
    this.status = {
      status: '',
      deliveryID: 0,
      location: undefined,
      statusDate: undefined
    };
  }

  clickCancelButton(): void {
    this.clearForm();
    this.update = false;
  }

  clickDeleteButton(id: number): void {
    if (confirm('Are you sure you want to delete this delivery status?')) {
      this.service.deleteDeliveryStatusByID(id).subscribe({
        next: () => {
          this.ngOnInit();
        },
        error: (error) => {
          console.error('Failed to delete delivery status', error);
        }
      });
    }
  }
}
