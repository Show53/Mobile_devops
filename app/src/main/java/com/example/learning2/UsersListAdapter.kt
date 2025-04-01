import android.annotation.SuppressLint
import com.example.learning2.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning2.UsersListItem
import kotlin.Int

class UsersListAdapter(
    private val items: List<UsersListItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: UsersListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_list_item, parent, false)
        return UsersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val listItemUserName: TextView = itemView.findViewById(R.id.list_item_user_name)
        private val listItemCardDate: TextView = itemView.findViewById(R.id.list_item_card_date)
        private val listItemDistanceCounter: TextView = itemView.findViewById(R.id.list_item_distance_counter)
        private val listItemTotalTimeCounter: TextView = itemView.findViewById(R.id.list_item_total_time_counter)
        private val listItemTrainName: TextView = itemView.findViewById(R.id.list_item_train_name)
        private val listItemDateTime: TextView = itemView.findViewById(R.id.list_item_date_time)

        @SuppressLint("SetTextI18n")
        fun bind(item: UsersListItem) {
            listItemUserName.text = item.nickname
            listItemCardDate.text = item.date.toString()
            listItemDistanceCounter.text = "${item.distance} км"
            listItemTotalTimeCounter.text = item.duration.toString()
            listItemTrainName.text = item.trainName
            listItemDateTime.text = item.endTime.toString()
        }
    }
}
