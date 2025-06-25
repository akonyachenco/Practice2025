import { Routes } from '@angular/router';
import { User } from './user/user';
import {Package} from './package/package';
import {Delivery} from './delivery/delivery';
import {CourierService} from './courier-service/courier-service';
import {DeliveryStatus} from './delivery-status/delivery-status';

export const routes: Routes = [
  { path: 'users', component: User},
  { path: 'packages', component: Package},
  { path: 'deliveries', component: Delivery},
  { path: 'courier-services', component: CourierService},
  { path: 'delivery-statuses', component: DeliveryStatus}
];
