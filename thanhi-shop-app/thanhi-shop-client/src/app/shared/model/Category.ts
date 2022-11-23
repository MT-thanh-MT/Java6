import {BaseEntity} from "./BaseEntity";

export class Category extends BaseEntity {
  public name?: string;
  public imageUrl?: string;

  constructor(name?: string,
              imageUrl?: string) {
    super();
    this.name = name;
    this.imageUrl = imageUrl;
  }
}
