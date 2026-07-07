<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="岗位" prop="positionId">
        <el-input
          v-model="queryParams.positionId"
          placeholder="请输入岗位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      
        <!-- <el-form-item label="岗位名称" prop="position_name"> 
        <el-select v-model="queryParams.positionId"@keyup.enter.native="handleQuery" clearable placeholder="请选择"  >
          <el-option
            v-for="item in positionList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item label="维度名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入维度名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="权重" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入权重"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="显示顺序" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入显示顺序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['interview:dimension:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['interview:dimension:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['interview:dimension:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['interview:dimension:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dimensionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="维度ID" align="center" prop="id" />  -->
      <el-table-column label="岗位" align="center" prop="positionName" />
      <el-table-column label="维度名称" align="center" prop="name" />
      <el-table-column label="维度描述" align="center" prop="description" width="350">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.description || '无'" placement="top" effect="light" :enterable="false">
            <span class="cell-text">{{ scope.row.description || '无' }}</span>
          </el-tooltip>
        </template>
        </el-table-column>
      <el-table-column label="权重" align="center" prop="weight" width="100"/>
      <el-table-column label="显示顺序" align="center" prop="sort"  width="100"/>
      <el-table-column label="状态" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['interview:dimension:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['interview:dimension:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改面试维度对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
          <el-form-item label="岗位名称" prop="position_name"> 
          <el-select v-model="form.position_name" placeholder="请选择">
            <el-option
              v-for="item in positionList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        </el-col>
          <!-- <el-col :span="24">
            <el-form-item label="关联岗位ID" prop="positionId">
              <el-input v-model="form.positionId" placeholder="请输入关联岗位ID" />
            </el-form-item>
          </el-col> -->
          <el-col :span="24">
            <el-form-item label="维度名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入维度名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="维度描述" prop="description">
              <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="权重" prop="weight">
              <el-input v-model="form.weight" placeholder="请输入权重" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="显示顺序" prop="sort">
              <el-input-number v-model="form.sort" controls-position="right"  :min="0" ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option
                  v-for="dict in dict.type.sys_normal_disable"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDimension, getDimension, delDimension, addDimension, updateDimension } from "@/api/interview/dimension"
import{listPositionAll}from "@/api/interview/position"
export default {
  name: "Dimension",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      positionList:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 面试维度表格数据
      dimensionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        positionId: null,
        name: null,
        description: null,
        weight: null,
        sort: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        positionId: [
          { required: true, message: "关联岗位ID不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "维度名称不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
    this.getlistAll()
  },
  watch: {
    
'$route.query': {
  handler() {
    
    // 处理路由参数
    const route = this.$route
    // 如果传入了岗位名称，直接使用
    if (route.query.positionName) {
      this.queryParams.name = route.query.positionName
    }
    this.getList()
  },
  deep: true,
  immediate: true
}
},
  methods: {
    getlistAll(){
      this.loading = true
      listPositionAll().then(response => {
        this.positionList = response
        this.total = response.total
        this.loading = false
      })

    },
    /** 查询面试维度列表 */
    getList() {
      this.loading = true
      listDimension(this.queryParams).then(response => {
        this.dimensionList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        positionId: null,
        name: null,
        description: null,
        weight: null,
        sort: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加面试维度"
      this.form.status="0"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getDimension(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改面试维度"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDimension(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addDimension(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除面试维度编号为"' + ids + '"的数据项？').then(function() {
        return delDimension(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('interview/dimension/export', {
        ...this.queryParams
      }, `dimension_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
<style scoped>
  .cell-text {
    display: inline-block;
    width: 100%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  </style>
