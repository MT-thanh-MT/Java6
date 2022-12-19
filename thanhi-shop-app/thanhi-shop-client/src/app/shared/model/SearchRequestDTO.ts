export class SearchRequestDTO {
  public text: string = "";
  public fields?: string[] = [];
  public limit: number = 5;

  constructor() {
  }

}
