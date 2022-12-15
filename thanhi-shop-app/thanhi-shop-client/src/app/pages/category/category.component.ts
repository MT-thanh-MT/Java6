import {Component, OnInit} from '@angular/core';
import {Category} from "../../shared/model/Category";
import {ActivatedRoute} from "@angular/router";
import {CategoryService} from "../../core/services/category.service";
import {ProductListComponent} from "../product/product-list/product-list.component";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categories: Category[] = [];

  constructor(private route: ActivatedRoute,
              public categoryService: CategoryService,
              public productListCom: ProductListComponent) {
    this.load();
  }

  ngOnInit(): void {
  }

  load() {
    this.categoryService.getCategories().subscribe(res => {
      this.categories = res;
    });
  }

  setCurrentCategory(name: string) {
    this.categoryService.currentCategoryName = name;
  }

}
