import { Component, OnInit } from '@angular/core';
import { DeliveryStatusService } from './delivery-status.service';
import { DeliveryStatusDto } from './delivery-status.dto';
import { FormsModule } from '@angular/forms';
import {DeliveryService} from '../delivery/delivery.service';

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
  errorMessage: string | null = null;
  findID: number | undefined = undefined;
  update: boolean = false;
  statuses: DeliveryStatusDto[] = [];
  status: DeliveryStatusDto = {
    status: "",
    deliveryID: 0
  };

  constructor(private service: DeliveryStatusService, private deliveryService: DeliveryService) { }

  ngOnInit(): void {
    if(this.findID != undefined) {
      this.clickFindByIDButton(this.findID);
    } else {
      this.service.getAllDeliveryStatuses().subscribe({
        next: (statuses) => {
          this.statuses = statuses;
        },
        error: (error) => {
          console.error('Failed to load delivery statuses:', error);
        }
      });
    }
  }

  clickUpdateButton(status: DeliveryStatusDto): void {
    this.errorMessage = null;
    window.scrollTo(0, 0);
    this.status = { ...status };
    this.update = true;
  }

  onSubmit() {
    this.errorMessage = null;
    if (this.update) {
      this.deliveryService.getDeliveryById(this.status.deliveryID).subscribe({
        error: (error) => {
          console.error('Failed to update delivery status:', error);
          this.errorMessage = error.error.message;
          this.service.getDeliveryStatusById(this.status.deliveryStatusID!).subscribe((status) => {
            this.status = status;
          });
          this.ngOnInit();
        }
      })

      this.service.updateDeliveryStatus(this.status).subscribe({
        next: () => {
          this.update = false;
          this.clearForm();
          this.ngOnInit();
        },
        error: () => {
          this.service.getDeliveryStatusById(this.status.deliveryStatusID!).subscribe((status) => {
            this.status = status;
          });
          this.ngOnInit();
        }
      })
    } else {
      this.service.createDeliveryStatus(this.status).subscribe({
        next: () => {
          this.ngOnInit();
          this.clearForm();
        },
        error: (error) => {
          console.error('Failed to create delivery status:', error);
          this.errorMessage = error.error.message;
        }
      });
    }
  }

  clearForm(): void {
    this.status = {
      status: "",
      deliveryID: 0
    };
  }

  clickCancelButton(): void {
    this.errorMessage = null;
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
          console.error('Failed to delete delivery status', error)
        }
      });
    }
  }

  clickFindByIDButton(findID: number): void {
    this.service.getDeliveryStatusById(findID).subscribe((status) => {
      this.statuses = [status];
    })
  }

  clickCancelFindButton(): void {
    this.findID = undefined;
    this.ngOnInit();
  }
}
