import {Component, OnInit} from '@angular/core';
import {NavbarComponent} from "../../../layout/navbar/navbar.component";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ProductService} from "../../../core/services/product.service";
import {Subject, takeUntil} from "rxjs";
import {NgbCarouselConfig} from "@ng-bootstrap/ng-bootstrap";
import {Product} from "../../../shared/model/Product";
import {CartService} from "../../../core/services/cart.service";
import {CategoryComponent} from "../../category/category.component";
import {CategoryService} from "../../../core/services/category.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  providers: [NgbCarouselConfig],
})
export class ProductListComponent implements OnInit {

  showNavigationArrows = false;
  showNavigationIndicators = false;
  private readonly destroy$ = new Subject<void>();

  page = 1;
  pageSize = 6;
  products: Product[] = [];

  constructor(public navbar: NavbarComponent,
              private route: ActivatedRoute,
              private productService: ProductService,
              config: NgbCarouselConfig,
              public cartService: CartService,
              public categoryService: CategoryService) {
    config.showNavigationArrows = true;
    config.showNavigationIndicators = true;
  }

  ngOnInit(): void {
    this.navbar.setActive('product-list');
    this.route.queryParams.subscribe(params => {
      this.load(params['cid' ?? 0]);
    });
    // this.route.params
    //   .pipe(takeUntil(this.destroy$))
    //   .subscribe((params: Params) => this.load(params['cid']));
  }

  load(cid?: number) {
    this.productService.getProducts(cid ?? 0).subscribe(res => {

      this.products = res;
    });
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product, 1);
  }

  equalsPrice(originPrice: number, sellPrice: number): boolean {
    return originPrice > sellPrice;
  }
}
