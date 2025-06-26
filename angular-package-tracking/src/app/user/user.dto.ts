import {PackageDto} from '../package/package.dto';
import {Validators} from '@angular/forms';

export interface UserDto {
  userID?: number;
  name: string;
  password: string;
  phone?: string;
  email: string;
  packages?: PackageDto[];
}
