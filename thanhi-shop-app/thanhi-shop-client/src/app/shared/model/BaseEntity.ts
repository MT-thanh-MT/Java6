/* This is base entity */
export class BaseEntity {
  public id?: number;
  public createDate?: Date = new Date();
  public modifiedDate?: Date = new Date();
  public createBy?: string;
  public modifiedBy?: string;

  constructor( id?: number,
               createDate?: Date,
               modifiedDate?: Date,
               createBy?: string,
               modifiedBy?: string) {
    this.id = id;
    this.createBy = createBy;
    this.createDate = createDate;
    this.modifiedDate = modifiedDate;
    this.modifiedBy = modifiedBy;
  }
}
