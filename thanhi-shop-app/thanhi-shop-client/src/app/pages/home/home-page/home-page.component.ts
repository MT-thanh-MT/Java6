import { Component, OnInit } from '@angular/core';
import {NavbarComponent} from "../../../layout/navbar/navbar.component";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../../core/services/product.service";
import {Product} from "../../../shared/model/Product";
import {CategoryService} from "../../../core/services/category.service";
import {Category} from "../../../shared/model/Category";
import {CartService} from "../../../core/services/cart.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css', '../../product/product-list/product-list.component.css']
})
export class HomePageComponent implements OnInit {

  page = 1;
  pageSize = 6;
  products: Product[] = [];
  categories: Category[] = [];

  constructor(public navbar: NavbarComponent,
              private route: ActivatedRoute,
              private productService: ProductService,
              public cartService: CartService,
              public categoryService: CategoryService) { }

  ngOnInit(): void {
    this.navbar.setActive('home');
    this.load(0);
  }

  load(cid?: number) {
    this.productService.getProducts(cid??0).subscribe(res => {
      this.products = res;
    });
    this.categoryService.getCategories().subscribe(res => this.categories = res);
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product, 1);
  }

  equalsPrice(originPrice: number, sellPrice: number): boolean {
    return originPrice > sellPrice;
  }
}
