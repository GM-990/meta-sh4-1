DESCRIPTION = "Tools for changing internal flash"
LICENSE = "CLOSED"

SRC_URI = "file://fw_env.config \
           file://fw_printenv \
           file://fw_setenv \
           ${@bb.utils.contains_any("MACHINE", "spark spark7162", "file://setspark.sh", "", d)} \
           file://flash_erase \
          "

FILES_${PN} = "/var/* /bin/* /sbin/*"

do_install () {
    install -d ${D}/var
    install -m 644 ${WORKDIR}/fw_env.config ${D}/var/
    install -d ${D}/bin
    install -m 755 ${WORKDIR}/fw_printenv ${D}/bin/
    install -m 755 ${WORKDIR}/fw_setenv ${D}/bin/
    if [ "${MACHINE}" = "spark" -o "${MACHINE}" = "spark7162" ]; then
        install -m 755 ${WORKDIR}/setspark.sh ${D}/bin/
    fi
    install -d ${D}/sbin
    install -m 755 ${WORKDIR}/flash_erase ${D}/sbin/
}
