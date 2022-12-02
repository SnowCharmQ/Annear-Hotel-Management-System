<template>
  <div>
    <!-- 添加区域 -->
    <div class="addType">
      <el-button type="primary" size="mini" class="addTypeButton" @click="addClick">Add Type</el-button>
    </div>
    <!-- 表格区域 -->
    <el-table :data="tableData" style="width: 98%; min-width: 1000px"
              :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '22px', fontFamily: 'nano',borderBottomColor: 'black' }"
              :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
              :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
              empty-text="No Room Added" :key="num">

      <el-table-column label="Name" width="100">
        <template slot-scope="scope">
          {{ scope.row.type_name }}
        </template>
      </el-table-column>
      <el-table-column label="Upper Limit" width="80">
        <template slot-scope="scope">
          {{ scope.row.upper_limit }}
        </template>
      </el-table-column>
      <el-table-column label="Breakfast" width="80">
        <template slot-scope="scope">
          {{ scope.row.breakfast }}
        </template>
      </el-table-column>
      <el-table-column label="Windows" width="120">
        <template slot-scope="scope">
          {{ scope.row.window }}
        </template>
      </el-table-column>
      <el-table-column label="Television" width="80">
        <template slot-scope="scope">
          {{ scope.row.television }}
        </template>
      </el-table-column>
      <el-table-column label="Bathtub" width="100">
        <template slot-scope="scope">
          {{ scope.row.bathtub }}
        </template>
      </el-table-column>
      <el-table-column label="Thermos" width="100">
        <template slot-scope="scope">
          {{ scope.row.thermos }}
        </template>
      </el-table-column>
      <el-table-column label="Discription" width="100">
        <template slot-scope="scope">
          {{ scope.row.discription }}
        </template>
      </el-table-column>

      <el-table-column label="Price" width="80">
        <template slot-scope="scope">
          {{ scope.row.price }}
        </template>
      </el-table-column>

      <el-table-column label="Area" width="80">
        <template slot-scope="scope">
          {{ scope.row.area }}
        </template>
      </el-table-column>
      <el-table-column label="Photo" width="180">
        <template slot-scope="scope">
          <img :src="scope.row.photo" width="100" height="100" class="head_pic"/>
          <!-- <img v-for="item in scope.row.photo" :src="item" width="80" height="80" class="head_pic"/> -->
        </template>
      </el-table-column>

      <el-table-column label="Operations">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="editClass(scope.$index)" class="editButton">Edit
          </el-button>
          <el-button size="mini" type="danger" @click="deleteClass(scope.$index)">Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.async="dialogFormVisible" :beforeClose="handleDialogFormClose">
      <el-form ref="form2" :model="form2" :rules="rules" label-width="100px">
        <el-form-item>
          <div class="form_header">
            <h1><b>Edit Room Type</b></h1>
          </div>
        </el-form-item>
        <el-form-item label="Type Name" prop="type_name">
          <el-input v-model="form2.type_name"></el-input>
        </el-form-item>
        <el-form-item label="Upper Limit" prop="price">
          <el-input v-model="form2.upper_limit"></el-input>
        </el-form-item>
        <el-form-item label="Television" prop="price">
          <el-input v-model="form2.television"></el-input>
        </el-form-item>
        <el-form-item label="Bathtub" prop="price">
          <el-input v-model="form2.bathtub"></el-input>
        </el-form-item>
        <el-form-item label="Thermos" prop="price">
          <el-input v-model="form2.thermos"></el-input>
        </el-form-item>
        <el-form-item label="Discription" prop="price">
          <el-input v-model="form2.discription"></el-input>
        </el-form-item>

        <el-form-item label="Price" prop="price">
          <el-input v-model="form2.price"></el-input>
        </el-form-item>

        <el-form-item label="Photo" prop="photo">
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :http-request="uploadPicture"
              :file-list="fileList"
              :limit=1
              list-type="picture">
            <el-button size="small" type="primary">Upload Picture</el-button>
            <div slot="tip" class="el-upload__tip">please upload .one jpg/png file</div>
          </el-upload>
        </el-form-item>

        <el-form-item>
          <div class="button_div">
            <el-button type="primary" size="small" @click="editForm('form2')">Edit</el-button>
            <el-button size="small" class="normal_button" @click="resetForm('form2')">Cancel
            </el-button>
          </div>
        </el-form-item>


      </el-form>
    </el-dialog>
    <div class="flex j-c" style="margin-top:5px">
      <el-pagination background layout="prev, pager, next"
                     :total="count"
                     :page-size="pageSize"
                     :current-page="pageIndex" class="pageClass"
                     @current-change="currentChange">
      </el-pagination>
    </div>

    <!-- 抽屉区域 -->
    <el-drawer title="Add a Room Type" text-align="center" :visible.sync="drawer" direction="rtl"
               :before-close="handleDrawerClose" class="drawerStyle" size="50%">
      <!-- 表单 -->

      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="Type Name" prop="name">
          <el-input v-model="form.type_name"></el-input>
        </el-form-item>
        <el-form-item label="Price" prop="price">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="Photo">
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :http-request="uploadPicture"
              :file-list="fileList"
              :limit=1
              list-type="picture">
            <el-button size="small" type="primary">Upload Picture</el-button>
            <div slot="tip" class="el-upload__tip">please upload .one jpg/png file</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="Upper Limit" prop="upper_limit">
          <el-input v-model="form.upper_limit"></el-input>
        </el-form-item>
        <el-form-item label="Breakfast" prop="breakfast">
          <el-input v-model="form.breakfast"></el-input>
        </el-form-item>
        <el-form-item label="Window" prop="window">
          <el-input v-model="form.window"></el-input>
        </el-form-item>
        <el-form-item label="Television" prop="television">
          <el-input v-model="form.television"></el-input>
        </el-form-item>
        <el-form-item label="Bathtub" prop="bathtub">
          <el-input v-model="form.bathtub"></el-input>
        </el-form-item>
        <el-form-item label="Thermos" prop="thermos">
          <el-input v-model="form.thermos"></el-input>
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input v-model="form.discription"></el-input>
        </el-form-item>
        <el-form-item label="area" prop="area">
          <el-input v-model="form.area"></el-input>
        </el-form-item>


        <el-form-item>
          <div class="button_div">
            <el-button type="primary" size="small" @click="submitForm('form')">Submit</el-button>
            <el-button size="small" class="normal_button" @click="resetForm('form')">Cancel
            </el-button>
          </div>
        </el-form-item>

      </el-form>
    </el-drawer>
  </div>
</template>

<script>


export default {
  data() {
    // var validateTypeName = (rule, value, callback) => {
    //
    //   if (value === '') {
    //     callback(new Error('Please enter Room Type.'));
    //   } else {
    //     let validName = /^[a-z A-Z.]+$/.test(value)
    //     if (!validName) {
    //       callback(new Error('Room Type must be a combination of letters'));
    //     } else {
    //       callback();
    //     }
    //   }
    // };

    var validatePrice = (rule, value, callback) => {

      if (value === '') {
        callback(new Error('Please enter the price.'));
      } else {
        let validName = /^[0-9.]+$/.test(value)
        if (!validName) {
          callback(new Error('Room Price must be a combination of digits'));
        } else {
          callback();
        }
      }
    };

    return {
      // 表格数据
      tableData: [],
      pageIndex: 1,
      pageSize: 1,
      num: '',
      count: 5,
      imageUrl: '',
      dialogFormVisible: false,
      form: {
        type_name: '',
        price: '',
        photo: '',
        upper_limit: '',
        breakfast: '',
        window: '',
        television: '',
        bathtub: '',
        discription: '',
        thermos: '',
        area: ''
      },
      form2: {
        typeId: '',
        type_name: '',
        price: '',
        photo: '',
        upper_limit: '',
        breakfast: '',

        window: '',
        television: '',
        bathtub: '',
        discription: '',
        thermos: '',
        index: -1
      },
      rules: {

        type_name: [
          // {validator: validateTypeName, trigger: 'blur'},

        ],
        price: [
          {validator: validatePrice, trigger: 'blur'},
        ]

      },
      fileList: [],
      // 是否打开抽屉
      drawer: false,
    }
  },
  created() {
    let params = {'token': sessionStorage.getItem('token')};
    this.$get(this.$baseUrl + '/auth/getUsernameByToken', params).then(data => {
      let resp = data.data;
      console.log(resp)
      let para = {'name': resp}
      this.$get(this.$baseUrl + '/room/room/hotel/initAdminRoom', para).then(data => {
        let resp = data.data
        console.log(resp)
        this.hotelId = resp.hotelId
        console.log(this.hotelId)
        this.getTableData();
      })
    })
  },
  methods: {
    getTableData() {
      let params = {}
      Object.assign(params, {
        page: this.pageIndex,
        limit: this.pageSize,
        hotel: this.hotelId,
      })
      this.tableData = []
      console.log('hi')
      console.log(params)
      this.$get(this.$baseUrl + '/room/room/roomtype/list', params).then(data => {
        let resp = data.data.list
        console.log(resp)
        this.count = resp.length
        for (let i = 0; i < resp.length; i++) {
          let item = resp[i]
          this.tableData.push({
            typeId: item.typeId,
            upper_limit: item.upperLimit,
            breakfast: item.breakfast,
            window: item.windows,
            television: item.television,
            bathtub: item.bathtub,
            thermos: item.thermos,
            discription: item.description,
            type_name: item.typeName,
            price: item.price,
            area: item.area,
            photo: item.photo
          })
        }
      })
    },
    addClick() {
      this.fileList = []
      this.drawer = true;
    },
    editClass(index) {
      this.fileList = []
      let item = this.tableData[index]
      this.form2.index = index
      this.form2.typeId = item.typeId
      this.form2.type_name = item.type_name
      this.form2.price = item.price
      this.form2.photo = item.photo
      this.form2.upper_limit = item.upper_limit
      this.form2.breakfast = item.breakfast
      this.form2.window = item.windows
      this.form2.television = item.television
      this.form2.bathtub = item.bathtub
      this.form2.thermos = item.thermos
      this.form2.type_name = item.type_name
      this.form2.discription = item.discription
      this.form2.area = item.area
      this.dialogFormVisible = true
    },
    deleteClass(index) {
      let item = this.tableData[index]
      let para = {'typeId': item.typeId}
      this.$get(this.$baseUrl + '/room/room/roomtype/deleteType', para).then(data => {
        console.log(data)
        if (data.state !== 200) {
          this.$message.error('can\'t be deleted')
        } else {
          this.tableData.splice(index, 1);
          this.$get(this.$baseUrl + '/room/room/roomtypepicture/deleteRoomPicture', {typeId: item.typeId}).then(data => {
            console.log('cus')
          })
        }
      }).catch(err => {
        this.$message.error('can\'t be deleted')
      })
    },
    handleDrawerClose(done) {
      done();
    },
    editForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.tableData[this.form2.index] = JSON.parse(JSON.stringify(this.form2))
          console.log(this.tableData[this.form2.index])
          // 为了更新表单
          let para = {
            typeId: this.form2.typeId,
            hotelId: this.hotelId,
            upperLimit: this.form2.upper_limit,
            price: this.form2.price,
            breakfast: this.form2.breakfast,
            windows: this.form2.window,
            television: this.form2.television,
            bathtub: this.form2.bathtub,
            thermos: this.form2.thermos,
            typeName: this.form2.type_name,
            description: this.form2.discription,
            area: this.form2.area
          }
          this.num = Math.random();
          this.$post(this.$baseUrl + '/room/room/roomtype/alterType', para).then(data => {
            console.log(data)
            if (data.state == 200) {
              this.$message.success("Edit Success")
              if (this.fileList) {
                let parm = {
                  typeId: this.form2.typeId,
                  picturePath: this.fileList[0].url,
                  cover: 1
                }
                this.$get(this.$baseUrl + '/room/room/roomtypepicture/addRoomPicture', parm).then(data => {
                  console.log('cus')
                })
              }
              this.getTableData()
            }
          })
          this.num = Math.random();
          console.log("hello world!");

        } else {
          console.log('error submit!!');
          return false;
        }
        this.$refs[formName].resetFields();
        this.dialogFormVisible = false;
      });
    },
    handleDialogFormClose() {

      // this.$refs['form2'].resetFields();
      this.dialogFormVisible = false;

    },
    currentChange(pageIndex) {
      this.pageIndex = pageIndex;
      this.getTableData()
    },
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {

          console.log(this.form)

          let para = {
            hotelId: this.hotelId,
            upperLimit: this.form.upper_limit,
            price: this.form.price,
            breakfast: this.form.breakfast,
            windows: this.form.window,
            television: this.form.television,
            bathtub: this.form.bathtub,
            thermos: this.form.thermos,
            typeName: this.form.type_name,
            description: this.form.discription,
            area: this.form.area
          }
          this.$post(this.$baseUrl + '/room/room/roomtype/addRoomType', para).then(data => {
            console.log(data)
            if (data.state == 200) {
              this.$message.success("Add success")
              let newId = data.data
              this.drawer = false;
              if (this.fileList) {
                let parm = {
                  typeId: newId,
                  picturePath: this.fileList[0].url,
                  cover: 1
                }
                this.$get(this.$baseUrl + '/room/room/roomtypepicture/addRoomPicture', parm).then(data => {
                  console.log('cus')
                })
              }
              this.getTableData()
            }
            console.log("hello world!");
          })

        } else {
          console.log('error submit!!');
          return false;
        }
        this.$refs[formName].resetFields();

      });
    },
    setRowStyle(row) {
      if (row.row.isPart == true) {
        return 'color:#1772b4;'
      }
    },
    async resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
    },
    // 上传成功
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('Avatar picture must be JPG format!');
      }
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!');
      }
      return isJPG && isLt2M;
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadPicture(file) {
      let picture = file.file
      let formData = new FormData();
      formData.append('file', picture)
      formData.append('orderId', this.selectedOrderId)
      let url = this.$baseUrl + '/auth/uploadPicture'

      this.$post(url, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(data => {
        console.log(data)
        let pictureUrl = data.data
        console.log(pictureUrl)
        this.fileList.push({name: picture.name, url: pictureUrl})
      });
    },

  }
}
</script>
<style scoped lang="scss">
.addTypeButton {
  font-family: 'nano';
  font-size: medium;
  color: black;
  border-color: black;
  background-color: #f3eee7;
}

.drawerStyle {
  color: #dc8917;
}

// 上传头像样式
.avatar-uploader {
  width: 178px;
  height: 178px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.el-button--primary {
  background-color: #f3eee7;
  border-color: #000000;
  color: black;
}

.editButton {
  margin-top: 0px;
  font-family: 'nano';
  height: 30px;
  font-size: 15px;
  line-height: 0.4;
  background-color: #f3eee7;
  border: 1px solid rgb(0, 0, 0);
  text-align: center;
  color: black;


  span {
    font-family: 'nano';
    color: white;
    font-size: 14px;
    letter-spacing: 1px;
  }
}

.el-button--danger {
  background-color: #f15973;
  border-color: #ec9bad;
  font-family: 'nano';
  font-size: 15px;
}

.el-button--danger:hover {
  background-color: #e9173a;

}

::v-deep .el-table tbody tr:hover > td {
  background-color: #8dfae1;
  opacity: 70%;
}


.el-button--primary:hover {
  background-color: #fffefd;
}
</style>