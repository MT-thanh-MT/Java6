
export const AdminNavbarData = [
  {
    routeLink: 'dashboard',
    icon: 'fas fa-house-user',
    label: 'Dashboard',
    roles: ['ADMIN', 'STAFF']
  },
  {
    routeLink: 'product',
    icon: 'fas fa-box',
    label: 'Products management',
    roles: ['ADMIN', 'STAFF']
  },
  {
    routeLink: 'user-management',
    icon: 'fas fa-user',
    label: 'User management',
    roles: ['ADMIN']
  },
  {
    routeLink: 'order-management',
    icon: 'fas fa-file-invoice-dollar',
    label: 'Order management',
    roles: ['ADMIN', 'STAFF']
  },
  {
    routeLink: '/home',
    icon: 'fas fa-store',
    label: 'Back to shop',
    roles: ['ADMIN', 'STAFF']
  },

];
