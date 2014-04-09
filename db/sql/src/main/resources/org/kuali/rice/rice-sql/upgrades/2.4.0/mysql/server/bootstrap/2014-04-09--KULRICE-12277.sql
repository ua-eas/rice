-- Create new KR-NTFCN:Channel KIM type
INSERT INTO krim_typ_t (kim_typ_id, obj_id, ver_nbr, nmspc_cd, nm, srvc_nm, actv_ind) VALUES ('KR1002', uuid(), 1, 'KR-NTFCN', 'Channel', '{http://rice.kuali.org/ken/v2_0}channelPermissionTypeService', 'Y')
/
INSERT INTO krim_attr_defn_t (kim_attr_defn_id, obj_id, ver_nbr, nmspc_cd, nm, lbl, actv_ind, cmpnt_nm) VALUES ('KR1002', uuid(), 1, 'KR-NTFCN', 'Channel ID', 'Channel ID', 'Y', NULL)
/
INSERT INTO krim_typ_attr_t (kim_typ_attr_id, obj_id, ver_nbr, sort_cd, kim_typ_id, kim_attr_defn_id, actv_ind) VALUES ('KR1005', uuid(), 1, 'a', (SELECT kim_typ_id FROM krim_typ_t WHERE nmspc_cd = 'KR-NTFCN' AND nm = 'Channel'), (SELECT kim_attr_defn_id FROM krim_attr_defn_t WHERE nmspc_cd = 'KR-NTFCN' AND nm = 'Channel ID'), 'Y')
/

-- Create KR-NTFCN:View Notification permission template
INSERT INTO krim_perm_tmpl_t (perm_tmpl_id, obj_id, ver_nbr, nmspc_cd, nm, desc_txt, kim_typ_id, actv_ind) VALUES ('KR1005', uuid(), 1, 'KR-NTFCN', 'View Notification', 'View KEN notifications', (SELECT kim_typ_id FROM krim_typ_t WHERE nmspc_cd = 'KR-NTFCN' AND nm = 'Channel'), 'Y')
/
