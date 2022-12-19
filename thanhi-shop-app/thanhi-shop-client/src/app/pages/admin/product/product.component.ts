import {Component, OnInit} from '@angular/core';
import {Product} from "../../../shared/model/Product";
import {ProductManagerService} from "../../../core/services/product-manager.service";
import {Router} from "@angular/router";
import {NgbModal, NgbModalConfig} from "@ng-bootstrap/ng-bootstrap";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastService} from "../../../shared/toast/toast-service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers: [NgbModalConfig, NgbModal],
})
export class ProductComponent implements OnInit {

  page = 1;
  pageSize = 5;
  products: Product[] = [];
  productModal!: Product;
  imageUrl: string = "a43bfe0a.jpg";
  modalTitle: string = 'Modal';
  form!: FormGroup;
  loading = false;
  submitted = false;

  constructor(private productManagerService: ProductManagerService,
              private router: Router,
              config: NgbModalConfig,
              private toast: ToastService,
              private modalService: NgbModal,
              private formBuilder: FormBuilder) {
    config.backdrop = 'static';
    config.keyboard = false;

  }

  ngOnInit(): void {
    this.load();
    this.form = this.createProductForm(this.productModal ?? new Product());
  }

  private createProductForm(product: Product) {
    return this.formBuilder.group(
      {
        id: [product.id],
        name: [product.name, [Validators.required, Validators.maxLength(255)]],
        imageUrl: [product.imageUrl,],
        file: ['', [Validators.required]],
        sellPrice: [product.sellPrice, [Validators.required, Validators.min(1)]],
        originPrice: [product.originPrice, [Validators.required, Validators.min(1)]],
        statusId: [product.statusId],
        subCateId: [product.subCateId],
        hot: [product.hot],
        quantity: [product.quantity, [Validators.required, Validators.min(0)]],
        description: [product.description, [Validators.required, Validators.minLength(1), Validators.maxLength(500)]],
        sold: [product.sold, [Validators.required, Validators.min(0)]],
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  load() {
    this.productManagerService.getAllProducts().subscribe(
      (res) => {
        this.products = res;
      },
      (error) => {
        console.log(error);
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
      }
    );
  }

  openModal(content: any, product: Product | null) {
    if (product) {
      this.productModal = product;
      this.modalTitle = 'Update new product';
      this.form = this.createProductForm(product);

    } else if (product == null){
      this.modalTitle = 'Create new product';
      this.form = this.createProductForm(new Product());
    }
    this.modalService.open(content, {size: 'xl'});
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      console.log(this.form.status);
      return;
    } else {
      const product = {
        ...this.form.value
      }
      console.log(product)
      product.imageUrl = this.imageUrl;
      let message = product.id == null ? 'Create new product Success.': 'Update product Success.';
      let errorMessage = product.id ? 'Create new product fail. ': 'Update product fail. ';
      this.productManagerService.save(product).subscribe(
        (res) => {
          if (product.id) {
            let index = this.products.findIndex(p => p.id == product.id);
            this.products[index] = res;
          } else {
            this.products.push(res);
          }
          this.toast.show(message, {classname: 'bg-success text-light', delay: 5000});
        },
        (error) => {
          if (error.status == 401) {
            this.router.navigate(['/logout']);
            this.toast.show('Your session is timed out. please sing in again', {
              classname: 'bg-danger text-light',
              delay: 5000
            });
          }
          this.toast.show(errorMessage + error.error.message, {classname: 'bg-danger text-light', delay: 5000});
        }
      );
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  onFileChange($event: Event) {
    const element = $event.target;
    // @ts-ignore
    let file: File | null = element.files[0];
    if (file) {
      this.imageUrl = file.name;
      this.form.value.file = file;
      this.form.value.imageUrl = this.imageUrl;
    }
    console.log(this.form.value)
  }

  delete(id: number) {
    Swal.fire({
      title: 'Do you want to delete product id: ' + id,
      showCancelButton: true,
      confirmButtonText: 'Delete'
    }).then((result) => {
      if (result.isConfirmed) {
        this.productManagerService.delete(id).subscribe(
          (res) => {
            let index = this.products.findIndex(product => product.id == id);
            this.products.splice(index, 1);
            this.toast.show('Delete product Success.', {classname: 'bg-success text-light', delay: 5000});
          },
          (error) => {
            console.log(error)
            this.toast.show(error.message, {classname: 'bg-danger text-light', delay: 5000});
          }
        )
      }
    })
  }


  searchByNameOrId(value: string) {
    this.productManagerService.searchProduct(value).subscribe(
      (res) => {
        console.log(res)
        this.products = res;
      },
      (error) => {
        console.log(error);
        if (error.status == 401) {
          this.router.navigate(['/access-denied']);
        }
      }
    );
  }
}
