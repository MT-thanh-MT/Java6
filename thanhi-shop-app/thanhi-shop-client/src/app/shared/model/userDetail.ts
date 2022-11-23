import {BaseEntity} from "./BaseEntity";

export class UserDetail extends BaseEntity {
  public username?: string;
  public password?: string;
  public firstName?: string;
  public lastName?: string;
  public email?: string;
  public activated: boolean = true;
  public roles: string[] = [];

  constructor(username?: string,
              password?: string,
              firstName?: string,
              lastName?: string,
              email?: string,
              activated: boolean = true,
              roles: string[] = []) {
    super();
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.activated = activated;
    this.roles = roles;
  }
}
