import {PackageDto} from '../package/package.dto';

export interface UserDto {
  userID?: number;
  name: string;
  passwordHash: string;
  phone?: string;
  email: string;
  packages?: PackageDto[];
}
