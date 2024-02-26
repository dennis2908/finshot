import Vue from "vue";
import Router from "vue-router";

// Containers
const TheContainer = () => import("@/containers/TheContainer");

//User Data
const Peran = () => import("@/views/peran/Peran");
Vue.use(Router);

export default new Router({
  mode: "hash", // https://router.vuejs.org/api/#mode
  linkActiveClass: "active",
  scrollBehavior: () => ({ y: 0 }),
  routes: configRoutes()
});

function configRoutes() {
  let urldir = "/peran/list";

  let peran = { path: "/peran/list", redirect: { name: "Home" } };

  peran = {
    path: "peran",
    redirect: "peran",
    name: "List Karyawan",
    component: {
      render(c) {
        return c("router-view");
      }
    },
    children: [
      {
        path: "list",
        component: {
          render(c) {
            return c("router-view");
          }
        },
        children: [
          {
            path: "",
            component: Peran
          }
        ]
      }
    ]
  };

  return [
    {
      path: "/",
      redirect: urldir,
      name: "Home",
      label: "User Management",
      component: TheContainer,
      children: [peran]
    }
  ];
}
