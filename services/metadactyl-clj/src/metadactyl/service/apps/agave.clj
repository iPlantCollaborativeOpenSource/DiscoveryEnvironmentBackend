(ns metadactyl.service.apps.agave
  (:use [kameleon.uuids :only [uuidify]])
  (:require [metadactyl.service.apps.agave.listings :as listings]
            [metadactyl.service.apps.agave.pipelines :as pipelines]
            [metadactyl.service.util :as util]))

(deftype AgaveApps [agave user-has-access-token?]
  metadactyl.protocols.Apps

  (getClientName [_]
    "agave")

  (listAppCategories [_ {:keys [hpc]}]
    (when-not (and hpc (.equalsIgnoreCase hpc "false"))
      [(.hpcAppGroup agave)]))

  (hasCategory [_ category-id]
    (= category-id (uuidify (:id (.hpcAppGroup agave)))))

  (listAppsInCategory [_ category-id params]
    (when (= category-id (uuidify (:id (.hpcAppGroup agave))))
      (listings/list-apps agave category-id params)))

  (searchApps [_ search-term params]
    (when (user-has-access-token?)
      (listings/search-apps agave search-term params)))

  (canEditApps [_]
    false)

  (listAppIds [_]
    nil)

  (getAppJobView [_ app-id]
    (when-not (util/uuid? app-id)
      (.getApp agave app-id)))

  (getAppDescription [_ app-id]
    (when-not (util/uuid? app-id)
      (:description (.getApp agave app-id))))

  (getAppDetails [_ app-id]
    (when-not (util/uuid? app-id)
      (.getAppDetails agave app-id)))

  (isAppPublishable [_ app-id]
    (when-not (util/uuid? app-id)
      false))

  (getAppTaskListing [_ app-id]
    (when-not (util/uuid? app-id)
      (.listAppTasks agave app-id)))

  (getAppToolListing [_ app-id]
    (when-not (util/uuid? app-id)
      (.getAppToolListing agave app-id)))

  (formatPipelineTasks [_ pipeline]
    (pipelines/format-pipeline-tasks agave pipeline)))
