import { StateOrProvince } from '../models/StateOrProvince';

export async function getStateOrProvincesByCountry(countryId: number): Promise<StateOrProvince[]> {
  const url = `/api/location/backoffice/state-or-provinces?countryId=${countryId}`;
  const response = await fetch(url);
  return await response.json();
}

export async function getPageableStateOrProvinces(
  pageNo: number,
  pageSize: number,
  countryId: number
) {
  const url = `/api/location/backoffice/state-or-provinces/paging?pageNo=${pageNo}&pageSize=${pageSize}&countryId=${countryId}`;
  const response = await fetch(url);
  return await response.json();
}

export async function createStateOrProvince(stateOrProvince: StateOrProvince) {
  const response = await fetch('/api/location/backoffice/state-or-provinces', {
    method: 'POST',
    body: JSON.stringify(stateOrProvince),
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
  return response;
}
export async function getStateOrProvince(id: number) {
  const response = await fetch('/api/location/backoffice/state-or-provinces/' + id);
  return await response.json();
}

export async function deleteStateOrProvince(id: number) {
  const response = await fetch(`/api/location/backoffice/state-or-provinces/${id}`, {
    method: 'DELETE',
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
  if (response.status === 204) return response;
  else return await response.json();
}

export async function editStateOrProvince(id: number, stateOrProvince: StateOrProvince) {
  const response = await fetch(`/api/location/backoffice/state-or-provinces/${id}`, {
    method: 'PUT',
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
    body: JSON.stringify(stateOrProvince),
  });
  if (response.status === 204) return response;
  else return await response.json();
}

export async function getStatesOrProvinces(id: number) {
  const response = await fetch(`/api/location/storefront/state-or-provinces/${id}`);
  return response.json();
}
