<template>
  <div>
    <CRow>
      <CCol sm="12">
        <CCard style="overflow: scroll">
          <CForm novalidate style="height: 500px">
            <CCardBody>
              <CInput
                type="hidden"
                required
                v-model="storeDM.state.formData.id"
              />
              <CInput
                id="number"
                type="text"
                autofocus
                v-model="storeDM.state.formData.number"
                description="Please enter your number."
                autocomplete="number"
                label="Nomor"
                placeholder="Enter number..."
                required
                valid-feedback="Great!!"
                :invalid-feedback="formDataMessage.number"
                :is-valid="validatorNumber"
              />
              <CInput
                type="text"
                v-model="storeDM.state.formData.position"
                description="Please enter your position."
                autocomplete="position"
                label="Posisi"
                placeholder="Enter position..."
                required
                valid-feedback="Great!!"
                :invalid-feedback="formDataMessage.position"
                :is-valid="validatorPosition"
              />
              <CInput
                type="text"
                v-model="storeDM.state.formData.name"
                description="Please enter your name."
                autocomplete="name"
                label="Nama"
                placeholder="Enter name..."
                required
                valid-feedback="Great!!"
                :invalid-feedback="formDataMessage.name"
                :is-valid="validatorName"
              />
              <CInput
                @keypress="onlyNumber($event)"
                type="text"
                v-model="storeDM.state.formData.phone"
                description="Please enter your phone."
                autocomplete="phone"
                label="Kontak"
                placeholder="Enter phone..."
                required
                valid-feedback="Great!!"
                :invalid-feedback="formDataMessage.phone"
                :is-valid="validatorPhone"
              />
              <CInput
                type="text"
                v-model="storeDM.state.formData.email"
                description="Please enter your email."
                autocomplete="email"
                label="Email"
                placeholder="Enter email..."
                required
                valid-feedback="Great!!"
                :invalid-feedback="formDataMessage.email"
                :is-valid="validatorEmail"
              />
            </CCardBody>
            <CCardFooter>
              <CButton
                type="button"
                @click="submit($event)"
                size="sm"
                color="primary"
              >
                <CIcon name="cil-check-circle" /> Submit
              </CButton>
            </CCardFooter>
          </CForm>
        </CCard>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import { storeDM } from "../../redux/storePeran";
export default {
  name: "FormsUserCreate",
  data() {
    return {
      storeDM: storeDM,
      formDataMessage: {
        number: "",
        position: "",
        name: "",
        phone: "",
        email: ""
      }
    };
  },
  watch: {
    value(value) {
      console.log(value);
      // or generate/simulate a native events (not sure how, but its outside Vue's realm I think
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.$refs.myInput.focus();
    });
  },
  props: ["closeFormModal", "loadMyData"],
  methods: {
    submit(event) {
      if (
        storeDM.state.formData.number !== "" &&
        storeDM.state.formData.position !== "" &&
        storeDM.state.formData.name !== "" &&
        storeDM.state.formData.phone !== "" &&
        storeDM.state.formData.email !== ""
      ) {
        if (!storeDM.state.formData.id) {
          delete storeDM.state.formData.id;
          fetch("http://localhost:8080/api/users", {
            method: "post",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json"
            },
            body: JSON.stringify(storeDM.state.formData)
          })
            .then((res) => res.json())
            .then((result) => {
              this.$emit("save");
              this.closeModal();
              this.loadMyData();
            });
        } else {
          fetch(
            "http://localhost:8080/api/users/" + storeDM.state.formData.id,
            {
              method: "put",
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
              },
              body: JSON.stringify(storeDM.state.formData)
            }
          )
            .then((res) => res.json())
            .then((result) => {
              console.log(21313132);
              this.$emit("save");
              this.closeModal();
              this.loadMyData();
            });
        }
      }
    },
    onlyNumber($event) {
      //console.log($event.keyCode); //keyCodes value
      let keyCode = $event.keyCode ? $event.keyCode : $event.which;
      if ((keyCode < 48 || keyCode > 57) && keyCode !== 46) {
        // 46 is dot
        $event.preventDefault();
      }
    },
    closeModal() {
      this.closeFormModal();
    },
    validatorNumber(val) {
      this.formDataMessage.number = "";
      if (typeof val !== "undefined") {
        if (val.length < 3)
          this.formDataMessage.number = "Please provide at least 3 characters.";
      }

      if (this.formDataMessage.number) return false;
      else return val;
    },
    validatorPosition(val) {
      this.formDataMessage.position = "";
      if (typeof val !== "undefined") {
        if (val.length < 3)
          this.formDataMessage.position =
            "Please provide at least 3 characters.";
      }

      if (this.formDataMessage.position) return false;
      else return val;
    },
    validatorName(val) {
      this.formDataMessage.name = "";
      if (typeof val !== "undefined") {
        if (val.length < 3)
          this.formDataMessage.name = "Please provide at least 3 characters.";
      }

      if (this.formDataMessage.name) return false;
      else return val;
    },
    validatorPhone(val) {
      this.formDataMessage.phone = "";
      if (typeof val !== "undefined") {
        if (val.length != 12)
          this.formDataMessage.phone = "Please provided 12 characters.";
        if (val.length != 12)
          this.formDataMessage.phone = "Please provided 12 characters.";
      }

      if (this.formDataMessage.phone) return false;
      else return val;
    },
    validatorEmail(val) {
      this.formDataMessage.email = "";
      if (typeof val !== "undefined") {
        if (!/^[^@]+@\w+(\.\w+)+\w$/.test(val))
          this.formDataMessage.email = "Please provide a valid email address";
      }

      if (this.formDataMessage.email) return false;
      else return val;
    }
  }
};
</script>
