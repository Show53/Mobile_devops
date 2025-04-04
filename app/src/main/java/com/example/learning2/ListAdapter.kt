import com.example.learning2.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning2.ListItem
import java.time.format.DateTimeFormatter
import kotlin.Int


class ListAdapter(
    private var items: List<ListItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ListItem)
    }

    fun updateList(newItems: List<ListItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onItemClick(item) }
    }
    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityNameTextView: TextView = itemView.findViewById(R.id.list_item_train_name)
        val dateTextView: TextView = itemView.findViewById(R.id.list_item_date_time)
        val distanceTextView: TextView = itemView.findViewById(R.id.list_item_distance_counter)
        val durationTextView: TextView = itemView.findViewById(R.id.list_item_total_time_counter)
        val cardDateTextView: TextView = itemView.findViewById(R.id.list_item_card_date)

        fun bind(item: ListItem) {
            activityNameTextView.text = item.trainName
            dateTextView.text = item.date.toString()
            distanceTextView.text = "${item.distance} км"
            val minutes = item.duration.toMinutes()
            val seconds = item.duration.minusMinutes(minutes).seconds
            durationTextView.text = String.format("%02d:%02d", minutes, seconds)
            val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
            cardDateTextView.text = item.date.format(formatter)
        }
    }
}