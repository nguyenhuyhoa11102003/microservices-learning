import { stringify } from 'querystring';
import { ProductAttributeGroup } from '../models/ProductAttributeGroup';

export async function getProductAttributeGroups(): Promise<ProductAttributeGroup[]> {
  const response = await fetch('/api/product/backoffice/product-attribute-groups');
  return await response.json();
}

export async function getPageableProductAttributeGroups(pageNo: number, pageSize: number) {
  const url = `/api/product/backoffice/product-attribute-groups/paging?pageNo=${pageNo}&pageSize=${pageSize}`;
  const response = await fetch(url);
  return await response.json();
}

export async function getProductAttributeGroup(id: number) {
  const response = await fetch('/api/product/backoffice/product-attribute-groups/' + id);
  return await response.json();
}

export async function createProductAttributeGroup(productAttributeGroup: ProductAttributeGroup) {
  const response = await fetch('/api/product/backoffice/product-attribute-groups', {
    method: 'POST',
    body: JSON.stringify(productAttributeGroup),
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
  return response;
}

export async function updateProductAttributeGroup(
  id: number,
  productAttributeGroup: ProductAttributeGroup
) {
  const response = await fetch('/api/product/backoffice/product-attribute-groups/' + id, {
    method: 'PUT',
    body: JSON.stringify(productAttributeGroup),
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
  if (response.status === 204) return response;
  else return await response.json();
}

export async function deleteProductAttributeGroup(id: number) {
  const response = await fetch('/api/product/backoffice/product-attribute-groups/' + id, {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json; charset=utf-8' },
  });
  if (response.status === 204) return response;
  else return await response.json();
}
