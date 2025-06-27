import {DeliveryDto} from '../delivery/delivery.dto';

export interface PackageDto {
  packageID?: number;
  trackingNumber: string;
  weight?: number;
  dimensions?: string;
  description?: string
  userID: number;
  deliveries:DeliveryDto[];
}
