export interface DeliveryStatusDto {
  deliveryStatusID?: number;
  status: string;
  location?: string;
  statusDate?: Date;
  deliveryID: number;
}
