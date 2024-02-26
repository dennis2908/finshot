let dataList = [];

dataList.push({
  name: "List Karyawan",
  to: "/peran/list"
});

var data = {
  _name: "CSidebarNav",
  _children: [
    {
      _name: "CSidebarNavDropdown",
      route: "/userdata",
      name: "List",
      icon: "cil-puzzle",
      items: dataList
    },
    {
      _name: "CSidebarNavDivider",
      _class: "m-2"
    }
  ]
};

export default [data];
