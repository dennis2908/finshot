<template>
  <CRow>
    <CCol col="12" xl="12">
      <CCard>
        <CCardHeader>
          <CForm class="row g-3">
            <div class="col-auto mr-3">
              <div class="row g-3 align-items-center" style="height: 50px">
                <CButton @click="getModalAddForm()" color="info" size="sm">
                  <svg
                    width="14"
                    height="22"
                    fill="currentColor"
                    class="bi bi-person-plus-fill"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"
                    />
                    <path
                      fill-rule="evenodd"
                      d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"
                    />
                  </svg>
                  Tambah Data
                </CButton>
                <div class="col-auto">
                  <download-excel :data="items">
                    <CButton color="success" size="sm">
                      Download Excel
                    </CButton>
                  </download-excel>
                </div>
              </div>
            </div>
            <div class="col-auto">
              <div
                class="row g-3 align-items-center bg-info"
                style="height: 50px"
              >
                <div class="col-auto">
                  <label for="inputPassword6" class="col-form-label"
                    >Pencarian Nama Karyawan</label
                  >
                </div>
                <div class="col-auto">
                  <input
                    type="text"
                    v-model="searchByName"
                    class="form-control"
                    autoComplete="name"
                    col="5"
                  />
                </div>
                <div class="col-auto">
                  <Button
                    type="button"
                    @click="klikSearch()"
                    class="form-control"
                    size="sm"
                    color="primary"
                  >
                    <CIcon name="cil-check-circle" /> Search
                  </Button>
                </div>
              </div>
            </div>
          </CForm>
        </CCardHeader>
        <ModalAddEdit @open="submit()" :loadTheData="loadTheData" />
        <ModalConfirm @open="submitConfirm()" :loadTheData="loadTheData" />
        <CCardBody>
          <CDataTable
            hover
            striped
            :loading="loading"
            :items="items"
            :fields="fields"
          >
            <template #index="{ item, index }">
              <td>
                <CBadge color="info">
                  {{ index + 1 }}
                </CBadge>
              </td>
            </template>
            <template #button_td="{ item }">
              <td>
                <div v-if="item">
                  <CButton
                    type="submit"
                    @click="getModalEditForm(item)"
                    size="sm"
                    class="mr-2"
                    color="success"
                  >
                    <i class="cis-pencil"></i>
                    <svg
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-trash"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
                      />
                      <path
                        fill-rule="evenodd"
                        d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
                      />
                    </svg>
                    Edit</CButton
                  >
                  <CButton
                    type="submit"
                    size="sm"
                    color="danger"
                    @click="getModalConfirm(item)"
                  >
                    <i class="cis-trash"></i>
                    <svg
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-trash"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
                      />
                      <path
                        fill-rule="evenodd"
                        d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
                      />
                    </svg>
                    Delete
                  </CButton>
                </div>
              </td>
            </template>
            <template #tgl_daftar="{ item }">
              <td>
                <div v-if="item">
                  <CBadge color="primary">
                    <div v-if="item">{{ changeDateFormat(item) }}</div>
                  </CBadge>
                </div>
              </td>
            </template>
            <template #id="{ item }">
              <td>
                <div v-if="item">
                  <CBadge color="primary">{{ primaryBadge(item.id) }}</CBadge>
                </div>
              </td>
            </template>
            <template #nama="{ item }">
              <td>
                <div v-if="item">
                  <CBadge color="primary">{{ primaryBadge(item.nama) }}</CBadge>
                </div>
              </td>
            </template>
          </CDataTable>
        </CCardBody>
      </CCard>
    </CCol>
  </CRow>
</template>

<script>
import Vue from "vue";
import JsonExcel from "vue-json-excel";
import { usersData, fields } from "./UsersData";
import ModalAddEdit from "@/views/ModalPeran/ModalAddEdit";
import ModalConfirm from "@/views/ModalPeran/ModalConfirm";
import { primaryBadge, hasWhiteSpace } from "../../genFunctions/genFunctions";
import { storeDM } from "../../redux/storePeran";

Vue.component("downloadExcel", JsonExcel);

export default {
  name: "Peran",
  components: { ModalAddEdit, ModalConfirm },
  data() {
    return {
      AksesHapus: false,
      storeDM: storeDM,
      searchByName: "",
      warningModal: false,
      primaryBadge: primaryBadge,
      items: usersData,
      loading: true,
      fields: fields,
      hasWhiteSpace: hasWhiteSpace
      //store:store,
    };
  },
  watch: {
    $route: {
      immediate: true,
      handler(route) {
        // if (route.query && route.query.page) {
        //   this.activePage = Number(route.query.page)
        // }
      }
    },
    items: function () {
      // console.log(this.items);
    }
  },
  mounted() {
    this.items = [];
    this.MyfetchData();
    document.getElementById("mynumber").focus();
  },
  methods: {
    loadTheData() {
      this.MyfetchData("");
    },
    klikSearch() {
      this.MyfetchData(this.searchByName);
    },
    MyfetchData(search = "") {
      // fetch("http://localhost:8080/api/users" + "?findUserByName=" + search)
      fetch(
        "http://localhost:8080/api/users/findUserByName" + "?name=" + search
      )
        .then((res) => res.json())
        .then((result) => {
          this.items = result.data;
          console.log(result.data);
          this.loading = false;
        });
    },
    getBadge(status) {
      switch (status) {
        case "Active":
          return "success";
        case "Inactive":
          return "secondary";
        case "Pending":
          return "warning";
        case "Banned":
          return "danger";
        default:
          "primary";
      }
    },
    changeDateFormat(item) {
      if (Object.keys(item).length > 0) {
        if (typeof item.tgl_daftar !== "undefined") {
          return item.tgl_daftar.substring(0, 10);
        }
      }
    },
    rowClicked(item, index) {
      // this.$router.push({ path: `/userdata/list/${index + 1}` })
    },
    submit() {
      console.log(12312132);
    },
    submitConfirm() {
      console.log(12312132);
    },
    getModalAddForm() {
      this.storeDM.commit("EmptyFormData");
      this.storeDM.commit("changeWarningModal", { modalState: true });
      this.storeDM.commit("changeTitleModal", { title: "Tambah Peran Baru" });
      document.getElementById("number").focus();
      //store.dispatch({ type: 'CHANGE_STATE', payload: { titleModal:"Add New Data" } })
    },
    async getModalEditForm(item) {
      await this.storeDM.commit("changeFormData", { getData: item });
      await this.storeDM.commit("changeWarningModal", { modalState: true });
      await this.storeDM.commit("changeTitleModal", {
        title: "Update Pengguna"
      });

      //store.dispatch({ type: 'CHANGE_STATE', payload: { titleModal:"Add New Data" } })
    },
    async getModalConfirm(item) {
      await this.storeDM.commit("changeFormData", { getData: item });
      this.storeDM.commit("changeConfirmModal", { modalState: true });
    }
  }
};
</script>
