import { INavData } from '@coreui/angular';

export const navItemsAdmin: INavData[] = [
  {
    title: true,
    name: 'Mantenimiento'
  },
  {
    name: 'Usuario',
    url: '/usuario',
    icon: 'icon-pencil'
  },
  {
    name: 'Proyecto',
    url: '/proyecto',
    icon: 'icon-pencil'
  }
];