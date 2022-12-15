import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../../core/services/product.service";
import {Product} from "../../../shared/model/Product";
import {ActivatedRoute} from "@angular/router";
import {ToastService} from "../../../shared/toast/toast-service";
import {CartService} from "../../../core/services/cart.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css','../product-list/product-list.component.css']
})
export class ProductDetailComponent implements OnInit {

  product!: Product;
  relatedProducts!: Product[];

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private toast: ToastService,
              public cartService: CartService,) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.load(parseInt(id));
  }

  load(id: number) {
      this.productService.getProductById(id).subscribe(
        (res) => {
          this.product = res;
          this.productService.getRelatedProduct(this.product.subCateId!).subscribe(
            (res) => this.relatedProducts = res
          );
          window.scroll({
            top: 0,
            left: 0,
            behavior: 'smooth'
          });
        },
        (error) => {
          this.toast.show('Something went wrong!', { classname: 'bg-danger text-light', delay: 5000 });
        }
      )
  }

  equalsPrice(originPrice: number, sellPrice: number):boolean {
    return originPrice > sellPrice;
  }

  addToCart(product: Product, qty: any) {
    let quantity: number = parseInt(qty);
    if (product.quantity! < quantity) {
      this.toast.show('INSUFFICIENT QUANTITY!', { classname: 'bg-danger text-light', delay: 5000 });
    } else {
      this.cartService.addToCart(product, quantity);
    }
  }
}
