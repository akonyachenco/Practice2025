import { Routes } from '@angular/router';
import { User } from './user/user';
import {Package} from './package/package';
import {Delivery} from './delivery/delivery';
import {CourierSevice} from './courier-sevice/courier-sevice';
import {DeliveryStatus} from './delivery-status/delivery-status';

export const routes: Routes = [
  { path: 'users', component: User},
  { path: 'packages', component: Package},
  { path: 'deliveries', component: Delivery},
  { path: 'courier-services', component: CourierSevice},
  { path: 'delivery-statuses', component: DeliveryStatus}
];
