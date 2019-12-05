@Override
	public List<CommStatisticsConfigForm> getOrg() {
		String tenantId = TenantIdHolder.get();
		StringBuilder sql = new StringBuilder()
				.append("select o.item_code AS orgId,o.item_name AS orgName from hsp.hsp_config_baseinfo o ,\r\n"
						+ "security.security_medical_vs_hsp p where o.item_code = p.hsp_code");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(tenantId)) {
			sql.append(" and p.medical_id=:tenantId ORDER BY o.seq_no ASC");
			map.put("tenantId", tenantId.trim());
		}
		return (List<CommStatisticsConfigForm>) findListBeanBySql(sql.toString(), map, CommStatisticsConfigForm.class);
	}